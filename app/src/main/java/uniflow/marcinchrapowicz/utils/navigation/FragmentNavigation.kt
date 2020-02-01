package uniflow.marcinchrapowicz.utils.navigation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <reified T : Fragment> FragmentActivity.openFragment(containerId: Int, addToBackStack: Boolean = true) {
    val last = supportFragmentManager.fragments.lastOrNull()
    if (last !is T) {
        val fragment: Fragment = supportFragmentManager.findFragmentByTag(T::class.java.simpleName)
                ?: createFragment<T>()
        commitNewFragment(fragment, containerId, addToBackStack)
    } else {
        Log.i("Tag", "skip openFragment - already open fragment: ${T::class}")
    }
}

fun FragmentActivity.commitNewFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean) {
    val transaction = supportFragmentManager.beginTransaction()
            .replace(containerId, fragment, fragment::class.java.simpleName)

    if (addToBackStack) {
        transaction.addToBackStack(fragment::class.java.simpleName)
    }

    transaction.commitAllowingStateLoss()
}

inline fun <reified T : Fragment> createFragment(): Fragment {
    val fragmentClass = T::class
    return fragmentClass.java.constructors.firstOrNull()?.newInstance() as? Fragment
            ?: error("can't create fragment $fragmentClass")
}

inline fun <reified T : Fragment> FragmentActivity.openFragment(containerId: Int, addToBackStack: Boolean = true, fragment: Fragment) {
    val last = supportFragmentManager.fragments.lastOrNull()
    if (last !is T) {
        commitNewFragment(fragment, containerId, addToBackStack)
    } else {
        Log.i("Tag", "skip openFragment - already open fragment: ${T::class}")
    }
}
