package the_null_pointer.secure_device.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import the_null_pointer.secure_device.data.datasource.DeviceDataSource
import the_null_pointer.secure_device.data.model.Device

class DeviceRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val deviceDataSource: DeviceDataSource
) : DeviceRepository {
    override suspend fun search(query: String): List<Device> = withContext(ioDispatcher) {
        return@withContext deviceDataSource.search(query)
    }

    override suspend fun getById(id: Long): Device? = withContext(ioDispatcher) {
        return@withContext deviceDataSource.getById(id)
    }

    override suspend fun getAll(): List<Device> = withContext(ioDispatcher) {
        return@withContext deviceDataSource.getAll()
    }
}