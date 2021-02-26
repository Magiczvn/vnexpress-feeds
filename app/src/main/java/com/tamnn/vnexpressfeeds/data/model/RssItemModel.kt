package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class RssItemModel {
    @get:Element
    @set:Element
    var title: String? = null

    @get:Element
    @set:Element
    var description: String? = null

    @get:Element
    @set:Element
    var pubDate: String? = null

    @get:Element
    @set:Element
    var link: String? = null

}