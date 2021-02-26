package com.tamnn.vnexpressfeeds.common

import androidx.collection.SimpleArrayMap
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class RxBus {

    private val _SubjectMaps = SimpleArrayMap<Class<out Any>, Subject<out Any>>()

    fun post(event: Any) {
        getSubject(event.javaClass).onNext(event)
    }

    fun <T : Any> register(clazz: Class<T>) = getSubject(clazz)

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> getSubject(clazz: Class<T>): Subject<T> = _SubjectMaps.get(clazz) as? Subject<T> ?: createSubject(clazz)

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> createSubject(clazz: Class<T>): Subject<T> {
        synchronized(_SubjectMaps) {
            val subject: Subject<T> = _SubjectMaps.get(clazz) as? Subject<T> ?: PublishSubject.create<T>().toSerialized()
            _SubjectMaps.put(clazz, subject)
            return subject
        }
    }
}