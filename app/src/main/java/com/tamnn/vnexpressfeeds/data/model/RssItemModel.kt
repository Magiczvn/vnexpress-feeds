package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class RssItemModel {
    @Element
    var title: String? = null

    @Element
    var description: String? = null

    @Element
    var pubDate: String? = null

    @Element
    var link: String? = null

    @Element(name = "slash:comments")
    var comments: Int? = null
}