package com.demo.mvvm.ui.decoration

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.mvvm.util.dp
import com.github.promeg.pinyinhelper.Pinyin
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by guoxiaodong on 3/16/21 10:17
 */
class DecorationActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = RecyclerView(this)
        setContentView(recyclerView)

        val (dataList, tagList) = generateDataList()

        recyclerView.adapter = DecorationAdapter(dataList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        val groupHeaderHeight = 40f.dp.toInt()
        val groupHeaderLeftPadding = 20f.dp.toInt()
        val groupHeaderItemDecoration = GroupHeaderItemDecoration(
            tagList, groupHeaderHeight, groupHeaderLeftPadding
        ) { canvas, paint, textPaint, rect, position ->
            canvas.drawRect(rect, paint)

            val src = BitmapFactory.decodeResource(resources, android.R.mipmap.sym_def_app_icon, null)
            val iconHeight = 20f.dp.toInt()
            val dst = Bitmap.createScaledBitmap(src, iconHeight, iconHeight, true)
            val left = rect.left.toFloat() + groupHeaderLeftPadding
            val top = rect.top + (groupHeaderHeight - iconHeight) / 2
            canvas.drawBitmap(dst, left, top.toFloat(), paint)

            val tag = tagList[position]
            val x = rect.left + groupHeaderLeftPadding + iconHeight + 10f.dp
            val bounds = Rect()
            textPaint.getTextBounds(tag, 0, tag.length, bounds)
            val y = rect.top + (groupHeaderHeight + bounds.height()) / 2
            canvas.drawText(tag, x, y.toFloat(), textPaint)
        }

        recyclerView.addItemDecoration(groupHeaderItemDecoration)
        recyclerView.addItemDecoration(DivideItemDecoration(tagList))
    }

    private fun generateDataList(): Pair<List<String>, List<String>> {
        val tagList = ArrayList<String>()
        return getOriginalDataList()
            .onEach { data ->// 生成首字母集合
                val fist = data.toCharArray()[0]
                tagList += when {
                    fist.toString().matches(Regex("[A-Za-z]")) -> fist.toString().toUpperCase(Locale.ROOT)
                    Pinyin.isChinese(fist) -> Pinyin.toPinyin(fist).substring(0, 1)
                    else -> "#"
                }
            }
            .zip(tagList)
            .sortedWith { o1, o2 ->// 按首字母排序
                when {
                    o1.second == "#" -> 1
                    o2.second == "#" -> -1
                    else -> o1.second.compareTo(o2.second)
                }
            }
            .unzip()
    }

    private fun getOriginalDataList(): List<String> {
        return listOf(
            "北京",
            "上海",
            "广州",
            "深圳",
            "西安",
            "成都",
            "南京",
            "三亚",
            "开封",
            "杭州",
            "嘉兴",
            "兰州",
            "新疆",
            "西藏",
            "昆明",
            "大理",
            "桂林",
            "东莞",
            "台湾",
            "香港",
            "澳门",
            "宝鸡",
            "蚌埠",
            "钓鱼岛",
            "安康",
            "苏州",
            "青岛",
            "郑州",
            "洛阳",
            "石家庄",
            "乌鲁木齐",
            "武汉",
            "←_←",
            "⊙﹏⊙",
            "Hello China",
            "宁波"
        )
    }
}