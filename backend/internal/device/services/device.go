package services

import "backend/models/entities"

type DeviceRepository interface {
	GetAll() ([]entities.Device, error)
	GetDeviceById(id string) (entities.Device, error)
	CreateDevice(device *entities.Device) error
	UpdateDevice(device *entities.Device) error
	DeleteDevice(device *entities.Device) error
}

type DeviceService struct {
	deviceRepository DeviceRepository
}

func NewDeviceService(deviceRepository DeviceRepository) *DeviceService {
	return &DeviceService{deviceRepository}
}

func (g *DeviceService) GetAll() ([]entities.Device, error) {
	return g.deviceRepository.GetAll()
}

func (g *DeviceService) GetDeviceById(id string) (entities.Device, error) {
	return g.deviceRepository.GetDeviceById(id)
}

func (g *DeviceService) CreateDevice(deviceType string) (entities.Device, error) {
	// TODO
	device := entities.Device{
		Type:           deviceType,
		Brand:          "",
		Model:          "",
		Video:          false,
		WiFi:           false,
		Ghz24:          false,
		Ghz5:           false,
		Protocol:       "",
		PrivacyShutter: false,
		Encryption:     "",
		IsSecure:       false,
		InfoLink:       "",
		Comments:       "",
	}
	err := g.deviceRepository.CreateDevice(&device)
	return device, err
}

func (g *DeviceService) UpdateDevice(device *entities.Device, deviceType string) error {
	// TODO
	device.Type = deviceType
	return g.deviceRepository.UpdateDevice(device)
}

func (g *DeviceService) DeleteDevice(device *entities.Device) error {
	return g.deviceRepository.DeleteDevice(device)
}
