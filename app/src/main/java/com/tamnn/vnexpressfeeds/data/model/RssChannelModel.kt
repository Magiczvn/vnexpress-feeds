package com.tamnn.vnexpressfeeds.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "channel", strict = false)
class RssChannelModel {
    @get:Element
    @set:Element
    var title: String? = null

    @get:Element
    @set:Element
    var description: String? = null

    @get:Element
    @set:Element
    var image: RssImageModel? = null

    @get:ElementList(inline = true, required = false)
    @set:ElementList(inline = true, required = false)
    var item: List<RssItemModel>? = null
}