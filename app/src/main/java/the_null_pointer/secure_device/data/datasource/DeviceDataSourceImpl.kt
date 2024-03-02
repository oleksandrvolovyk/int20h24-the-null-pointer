package the_null_pointer.secure_device.data.datasource

import the_null_pointer.secure_device.data.model.Device

class DeviceDataSourceImpl(
    private val deviceApi: DeviceApi
) : DeviceDataSource {
    override fun search(query: String): List<Device> {
        return try {
            val call = deviceApi.search(query)

            val response = call.execute()

            if (response.isSuccessful && response.body() != null) {
                response.body()!!.cleanComments()
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
                response.body()?.cleanComments()
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
                response.body()!!.cleanComments()
            } else {
                emptyList()
            }
        } catch (t: Throwable) {
            emptyList()
        }
    }

    private fun List<Device>.cleanComments(): List<Device> {
        return this.map {
            it.copy(comments = it.comments?.replace("\\n", "\n"))
        }
    }

    private fun Device.cleanComments(): Device {
        return this.copy(comments = this.comments?.replace("\\n", "\n"))
    }
}