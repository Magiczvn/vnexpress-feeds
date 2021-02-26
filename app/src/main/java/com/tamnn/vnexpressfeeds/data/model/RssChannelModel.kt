package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "channel", strict = false)
class RssChannelModel {
    @Element
    var title: String? = null

    @Element
    var description: String? = null

    @Element
    var image: RssImageModel? = null

    @ElementList(inline = true, required = false)
    var item: List<RssItemModel>? = null
}