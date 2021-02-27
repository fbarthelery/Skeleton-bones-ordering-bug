Skeleton-Bones ordering bug
===========================

This is a minimal sample project for this [bug](https://github.com/EudyContreras/Skeleton-Bones/issues/16) in [Skeleton-Bones](https://github.com/EudyContreras/Skeleton-Bones/) library.

Bug
===

**Describe the bug**
When using attribute `skeletonBoneIgnored` the binding adapter method `setSkeletonIgnored()` looks for the parent skeleton drawable to store the ignored id by calling `getParentSkeletonDrawable()`. If the parent skeleton drawable is not found nothing happens and the view is not ignored.

There is no guaranteed order of execution in Android data binding as stated  [here](https://stackoverflow.com/questions/40932933/android-bindingadapter-order-of-execution). Thus the call to `setSkeletonIgnored()` can occurs before the call to `setSkeletonEnabled()` or other binding adapter methods who creates the skeleton drawable and the view won't be ignored.

In the sample app, you can notice that skeleton bones properties don't get apply on the `do_more` button because of this.

**Expected behavior**
The view with `skeletonBoneIgnored="@{true}"` is ignored.

**Additional context**
Other binding adapter methods may be affected by this behavior. I think it also happens for `skeletonBoneHeight` ,`skeletonBoneMinHeight`

A workaround is to immediately create the `SkeletonDrawable ` programmatically  just after inflating the layout.

