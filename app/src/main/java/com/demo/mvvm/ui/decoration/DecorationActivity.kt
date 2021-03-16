package com.demo.mvvm.ui.decoration

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.promeg.pinyinhelper.Pinyin

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

        recyclerView.addItemDecoration(GroupHeaderItemDecoration(tagList))
        recyclerView.addItemDecoration(DivideItemDecoration(tagList))
    }

    private fun generateDataList(): Pair<List<String>, List<String>> {
        val tagList = ArrayList<String>()
        return getOriginalDataList()
            .onEach { data ->// 生成首字母集合
                val fist = data.toCharArray()[0]
                tagList += when {
                    fist.toString().matches(Regex("[A-Za-z]")) -> fist.toString().toUpperCase()
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