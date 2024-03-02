package the_null_pointer.secure_device.data

import the_null_pointer.secure_device.data.model.Device

interface DeviceRepository {
    suspend fun search(query: String): List<Device>
    suspend fun getById(id: Long): Device?
    suspend fun getAll(): List<Device>
}