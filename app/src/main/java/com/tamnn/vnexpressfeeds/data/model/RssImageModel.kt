package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
class RssImage {
    @Element
    var url: String? = null

    @Element
    var title: String? = null

    @Element
    var link: String? = null
}