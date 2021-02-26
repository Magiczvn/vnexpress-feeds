package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssFeedModel {
    @get:Element
    @set:Element
    var channel: RssChannelModel? = null
}