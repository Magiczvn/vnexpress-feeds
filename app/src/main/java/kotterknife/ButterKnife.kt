package kotterknife

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.View
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import androidx.fragment.app.DialogFragment as SupportDialogFragment
import androidx.fragment.app.Fragment as SupportFragment

fun View.bindDimenInt(id: Int)
        : ReadOnlyProperty<View, Int> = Lazy { t: View, _ -> t.resources.getDimension(id).toInt() }

fun View.bindBool(id: Int)
        : ReadOnlyProperty<View, Boolean> = Lazy { t: View, _ -> t.resources.getBoolean(id) }

fun Activity.bindDimenInt(id: Int)
        : ReadOnlyProperty<Activity, Int> = Lazy { t: Activity, _ -> t.resources.getDimension(id).toInt() }

fun SupportFragment.bindDimenInt(id: Int)
        : ReadOnlyProperty<SupportFragment, Int> = Lazy { t: SupportFragment, _ -> t.resources.getDimension(id).toInt() }

fun ViewHolder.bindDimenInt(id: Int)
        : ReadOnlyProperty<ViewHolder, Int> = Lazy { t: ViewHolder, _ -> t.itemView.resources.getDimension(id).toInt() }

fun Activity.bindBool(id: Int)
        : ReadOnlyProperty<Activity, Boolean> = Lazy { t: Activity, _ -> t.resources.getBoolean(id) }

fun SupportFragment.bindBool(id: Int)
        : ReadOnlyProperty<SupportFragment, Boolean> = Lazy { t: SupportFragment, _ -> t.resources.getBoolean(id) }

fun ViewHolder.bindBool(id: Int)
        : ReadOnlyProperty<ViewHolder, Boolean> = Lazy { t: ViewHolder, _ -> t.itemView.resources.getBoolean(id) }

fun SupportFragment.bindInt(id: Int)
        : ReadOnlyProperty<SupportFragment, Int> = Lazy { t: SupportFragment, _ -> t.resources.getInteger(id) }

fun ViewHolder.bindInt(id: Int)
        : ReadOnlyProperty<ViewHolder, Int> = Lazy { t: ViewHolder, _ -> t.itemView.resources.getInteger(id) }

fun ViewHolder.bindString(id: Int)
        : ReadOnlyProperty<ViewHolder, String> = Lazy { t: ViewHolder, _ -> t.itemView.resources.getString(id) }

fun Activity.bindString(id: Int)
        : ReadOnlyProperty<Activity, String> = Lazy { t: Activity, _ -> t.resources.getString(id) }

fun SupportFragment.bindString(id: Int)
        : ReadOnlyProperty<SupportFragment, String> = Lazy { t: SupportFragment, _ -> t.resources.getString(id) }

fun <V : View> View.bindView(id: Int)
        : ReadOnlyProperty<View, V> = required(id, viewFinder)

fun <V : View> ViewHolder.bindView(id: Int)
        : ReadOnlyProperty<ViewHolder, V> = required(id, viewFinder)

fun <V : View> View.bindOptionalView(id: Int)
        : ReadOnlyProperty<View, V?> = optional(id, viewFinder)

fun <V : View> ViewHolder.bindOptionalView(id: Int)
        : ReadOnlyProperty<ViewHolder, V?> = optional(id, viewFinder)

fun <V : View> View.bindViews(vararg ids: Int)
        : ReadOnlyProperty<View, List<V>> = required(ids, viewFinder)

fun <V : View> ViewHolder.bindViews(vararg ids: Int)
        : ReadOnlyProperty<ViewHolder, List<V>> = required(ids, viewFinder)

fun <V : View> View.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<View, List<V>> = optional(ids, viewFinder)

fun <V : View> ViewHolder.bindOptionalViews(vararg ids: Int)
        : ReadOnlyProperty<ViewHolder, List<V>> = optional(ids, viewFinder)

private val View.viewFinder: View.(Int) -> View?
    get() = { findViewById(it) }
private val ViewHolder.viewFinder: ViewHolder.(Int) -> View?
    get() = { itemView.findViewById(it) }

private fun viewNotFound(id: Int, desc: KProperty<*>): Nothing =
        throw IllegalStateException("View ID $id for '${desc.name}' not found.")

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> required(id: Int, finder: T.(Int) -> View?)
        = Lazy { t: T, desc -> t.finder(id) as V? ?: viewNotFound(id, desc) }

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> optional(id: Int, finder: T.(Int) -> View?)
        = Lazy { t: T, _ -> t.finder(id) as V? }

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> required(ids: IntArray, finder: T.(Int) -> View?)
        = Lazy { t: T, desc -> ids.map { t.finder(it) as V? ?: viewNotFound(it, desc) } }

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> optional(ids: IntArray, finder: T.(Int) -> View?)
        = Lazy { t: T, _ -> ids.map { t.finder(it) as V? }.filterNotNull() }

// Like Kotlin's lazy delegate but the initializer gets the target and metadata passed to it
private class Lazy<T, V>(private val initializer: (T, KProperty<*>) -> V) : ReadOnlyProperty<T, V> {
    private object EMPTY

    private var _Value: Any? = EMPTY

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        if (_Value == EMPTY) {
            _Value = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return _Value as V
    }
}
