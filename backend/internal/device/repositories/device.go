package repositories

import (
	"backend/models/entities"
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

func (g *DeviceRepository) GetDevicesByModelOrBrand(query string) ([]entities.Device, error) {
	var devices []entities.Device
	err := g.db.Model(&entities.Device{}).
		Find(&devices, "model ilike ? OR brand ilike ?", "%"+query+"%", "%"+query+"%")
	if len(devices) == 0 {
		err = g.db.Model(&entities.Device{}).
			Find(&devices, "levenshtein(model, ?) <= 4 OR levenshtein(brand, ?) <= 4 "+
				"order by levenshtein(model, ?), levenshtein(brand, ?) limit 10", query, query, query, query)
	}
	return devices, err.Error
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
