package swenson.creoit.com.amazonshoppingcart

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MyMovie(@SerializedName("title") val title : String,
                      @SerializedName("image") val image : String,
                      @SerializedName("price") val price : String) : Serializable