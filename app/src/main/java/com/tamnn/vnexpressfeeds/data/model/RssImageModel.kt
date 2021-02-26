package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
class RssImageModel {
    @get:Element
    @set:Element
    var url: String? = null
}