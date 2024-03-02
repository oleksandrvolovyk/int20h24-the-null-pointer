package the_null_pointer.secure_device.data.datasource

import the_null_pointer.secure_device.data.model.Device

class DeviceDataSourceImpl(
    private val deviceApi: DeviceApi
): DeviceDataSource {
    override fun search(query: String): List<Device> {
        return try {
            val call = deviceApi.search(query)

            val response = call.execute()

            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (t: Throwable) {
            emptyList()
        }
    }

    override fun getById(id: Long): Device? {
        return try {
            val call = deviceApi.getById(id)

            val response = call.execute()

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (t: Throwable) {
            null
        }
    }

    override fun getAll(): List<Device> {
        return try {
            val call = deviceApi.getAll()

            val response = call.execute()

            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (t: Throwable) {
            emptyList()
        }
    }
}