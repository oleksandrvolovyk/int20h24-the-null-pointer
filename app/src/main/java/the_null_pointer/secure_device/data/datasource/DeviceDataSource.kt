package the_null_pointer.secure_device.data.datasource

import the_null_pointer.secure_device.data.model.Device

interface DeviceDataSource {
    fun search(query: String): List<Device>
    fun getById(id: Long): Device?
    fun getAll(): List<Device>
}