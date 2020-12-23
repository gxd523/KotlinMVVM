package com.gxd523.mvvm.network

import com.qiniu.android.dns.DnsManager
import com.qiniu.android.dns.NetworkInfo
import com.qiniu.android.dns.local.AndroidDnsServer
import com.qiniu.android.dns.local.Resolver
import okhttp3.Dns
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


/**
 * DNS优化
 */
class HttpDns(private val timeout: Long) : Dns {
    private val dnsManager by lazy {
        DnsManager(
            NetworkInfo.normal,
            arrayOf(
                AndroidDnsServer.defaultResolver(),// 系统默认DNS服务器
                Resolver(InetAddress.getByName("119.29.29.29")),// 自定义DNS服务器地址
//                QiniuDns(accountId, encryptKey, expireTimeMs)// 七牛httpdns服务
            )
        )
    }

    override fun lookup(hostname: String): MutableList<InetAddress> {
        return try {
            Executors.newSingleThreadExecutor().submit(Callable<MutableList<InetAddress>> {
                dnsManager.query(hostname)
                    .flatMap {
                        InetAddress.getAllByName(it).asIterable()
                    }.ifEmpty {
                        Dns.SYSTEM.lookup(hostname)
                    }.toMutableList()
            }).get(timeout, TimeUnit.MILLISECONDS)
        } catch (e: Exception) {
            throw UnknownHostException("Unknow Host: $hostname")
        }
    }
}