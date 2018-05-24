package swenson.creoit.com.amazonshoppingcart

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_category_items.view.*
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.*

class CategoryVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindCategoryView(items : MyMovie, isCenterAligned : Boolean) {

        with(itemView) {

            if (isCenterAligned) {

                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL
                llBox.layoutParams = layoutParams
            }
            tvItemHeading.text = items.title
            tvDesc.text = items.price

            displayImages(items.image,ivCategory)


            //Glide.with(context).load(items.image).into(ivCategory)
        }


    }

    fun displayImages(url : String , imageView : ImageView) {

        Observable.fromCallable { getBitmapFromUrl(url)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread()).subscribe({imageView.setImageBitmap(it)},{})


    }

     fun getBitmapFromUrl(url : String) : Bitmap? {

        try {

            var inputStream = URL(url).openStream()
            return BitmapFactory.decodeStream(inputStream)

        }

        catch (e : IOException) {
            e.printStackTrace()
        }

        return null
    }
}