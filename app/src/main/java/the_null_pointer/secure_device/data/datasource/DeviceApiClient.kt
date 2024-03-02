package the_null_pointer.secure_device.data.datasource

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object DeviceApiClient {
    val client: Retrofit
        get() {
            val converterFactory = JacksonConverterFactory.create(
                ObjectMapper().configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false
                ).registerKotlinModule()
            )
            return Retrofit.Builder()
                .baseUrl("https://apricotka.com.ua/api/v1/")
                .addConverterFactory(converterFactory)
                .build()
        }
}