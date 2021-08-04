package org.thoughtcrime.securesms.util

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * A lifecycle-aware [Disposable] that, after being bound to a lifecycle, will automatically dispose all contained disposables at the proper time.
 */
class LifecycleDisposable : DefaultLifecycleObserver {
  val disposables: CompositeDisposable = CompositeDisposable()

  fun bindTo(lifecycle: Lifecycle): LifecycleDisposable {
    lifecycle.addObserver(this)
    return this
  }

  fun add(disposable: Disposable): LifecycleDisposable {
    disposables.add(disposable)
    return this
  }

  override fun onDestroy(owner: LifecycleOwner) {
    disposables.clear()
  }
}