package repositories

import (
	"backend/packages/models/entities"
	"gorm.io/gorm"
)

type DeviceRepository struct {
	db *gorm.DB
}

func NewDeviceRepository(db *gorm.DB) *DeviceRepository {
	return &DeviceRepository{db}
}

func (g *DeviceRepository) GetAll() ([]entities.Device, error) {
	var devices []entities.Device
	err := g.db.Find(&devices)
	return devices, err.Error
}

func (g *DeviceRepository) GetDeviceById(id string) (entities.Device, error) {
	var device entities.Device
	err := g.db.First(&device, id)
	return device, err.Error
}

func (g *DeviceRepository) CreateDevice(device *entities.Device) error {
	return g.db.Create(&device).Error
}

func (g *DeviceRepository) UpdateDevice(device *entities.Device) error {
	return g.db.Save(&device).Error
}

func (g *DeviceRepository) DeleteDevice(device *entities.Device) error {
	return g.db.Delete(&device).Error
}
