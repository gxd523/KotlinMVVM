package com.gxd523.mvvm.model.impl

import com.gxd523.mvvm.bean.ImageBean
import com.gxd523.mvvm.model.AbsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by guoxiaodong on 2020/10/5 15:20
 */
class NetworkModel : AbsModel() {
    private val imageUrlList = listOf(
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa4.att.hudong.com%2F52%2F35%2F01300000248079122537357453023.jpg&refer=http%3A%2F%2Fa4.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611216356&t=9c7ac87e14c5452eba3701919e5a6946",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg-article.pchome.net%2F00%2F43%2F75%2F06%2Fpic_lib%2Fwm%2Favatar_01.jpg&refer=http%3A%2F%2Fimg-article.pchome.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611216366&t=d8ce7e62ceb3ffadbf67323eac2471b0",
        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1188923622,400867403&fm=26&gp=0.jpg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa4.att.hudong.com%2F27%2F67%2F01300000921826141299672233506.jpg&refer=http%3A%2F%2Fa4.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611216386&t=c9b235253322b527ff93d62b2548d4fb",
        "https://imgsrc.baidu.com/forum/w=580/sign=5006d94c6b2762d0803ea4b790ed0849/78415edf8db1cb13b0be5a7cd554564e90584bf7.jpg",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic5.nipic.com%2F20100225%2F1399111_094253001130_2.jpg&refer=http%3A%2F%2Fpic5.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1611216361&t=314efef761448fbb1aac2f889a03de46"
    )

    suspend fun requestImageUrl(index: Int, flag: Boolean): ImageBean {
        return withContext(Dispatchers.IO) {
            val i = 1 / 0
            Thread.sleep(5000)// TODO: 12/22/20 模拟耗时
            val finalIndex = index * 2 + if (flag) 1 else 0
            ImageBean(imgUrl = imageUrlList[finalIndex])
        }
    }
}