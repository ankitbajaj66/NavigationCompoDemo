package com.example.navgraphnesting.flow1

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 *Created by Ankit Bajaj on 26-06-2020.
 */
class DrawableMatcher(@DrawableRes private val expectedId: Int) :
    TypeSafeMatcher<View>(View::class.java) {
    private var resourceName: String? = null

    override fun describeTo(description: Description?) {
        description?.let {
            it.appendText("with drawable from resource id: ")
            it.appendValue(expectedId)

            it.appendText("[");
            it.appendText(resourceName);
            it.appendText("]");
        }

    }

    override fun matchesSafely(target: View?): Boolean {
        //Type check we need to do in TypeSafeMatcher
        if (target !is ImageView) {
            return false

        }
        //We fetch the image view from the focused view

        val imageView = target

        if (expectedId < 0)
            return target.drawable == null

        //We get the drawable from the resources that we are going to compare with image view source
        val resources = target.resources
        val expectedDrawable = ResourcesCompat.getDrawable(resources, expectedId, null)
        resourceName = resources.getResourceEntryName(expectedId)

        // Check if Expected Drawable is null then return false
        if (expectedDrawable == null) false

        //comparing the bitmaps should give results of the matcher if they are equal
        val expectedBitmap = Bitmap.createBitmap(
            expectedDrawable?.intrinsicWidth!!,
            expectedDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val originalBitmap = Bitmap.createBitmap(
            target.drawable?.intrinsicWidth!!,
            target.drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
//        val expectedBitmap = (expectedDrawable as ColorDrawable)

        return originalBitmap.sameAs(expectedBitmap)
    }

    companion object {
        fun withDrawable(resourceID: Int): Matcher<View> = DrawableMatcher(resourceID)
    }
}