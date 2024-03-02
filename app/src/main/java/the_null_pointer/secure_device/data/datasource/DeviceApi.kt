package the_null_pointer.secure_device.data.datasource

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import the_null_pointer.secure_device.data.model.Device

interface DeviceApi {
    @GET("device")
    fun search(@Query("query") query: String): Call<List<Device>>

    @GET("device")
    fun getAll(): Call<List<Device>>

    @GET("device/{id}")
    fun getById(@Path("id") id: Long): Call<Device>
}