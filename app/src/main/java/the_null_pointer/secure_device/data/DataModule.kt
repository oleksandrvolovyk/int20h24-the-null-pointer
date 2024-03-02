package the_null_pointer.secure_device.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import the_null_pointer.secure_device.data.datasource.DeviceApi
import the_null_pointer.secure_device.data.datasource.DeviceApiClient
import the_null_pointer.secure_device.data.datasource.DeviceDataSource
import the_null_pointer.secure_device.data.datasource.DeviceDataSourceImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideDeviceRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        deviceDataSource: DeviceDataSource
    ): DeviceRepository {
        return DeviceRepositoryImpl(ioDispatcher, deviceDataSource)
    }

    @Provides
    @Singleton
    fun provideDeviceApi(): DeviceApi {
        return DeviceApiClient.client.create(DeviceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDeviceDatasource(
        deviceApi: DeviceApi
    ): DeviceDataSource {
        return DeviceDataSourceImpl(deviceApi)
    }
}