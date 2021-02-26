package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssFeed {
    @Element
    var channel: RssChannel? = null
}