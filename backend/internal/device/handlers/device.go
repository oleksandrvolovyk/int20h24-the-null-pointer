package handlers

import (
	"backend/packages/models/entities"
	"github.com/gin-gonic/gin"
	"net/http"
)

type DeviceService interface {
	GetAll() ([]entities.Device, error)
	GetDeviceById(id string) (entities.Device, error)
	CreateDevice(deviceType string) (entities.Device, error)
	UpdateDevice(device *entities.Device, deviceType string) error
	DeleteDevice(device *entities.Device) error
}

type DeviceHandler struct {
	deviceService DeviceService
}

func NewDeviceHandler(deviceService DeviceService) *DeviceHandler {
	return &DeviceHandler{deviceService}
}

func (g *DeviceHandler) GetAll(c *gin.Context) {
	devices, err := g.deviceService.GetAll()
	if err != nil {
		c.IndentedJSON(http.StatusBadRequest, err.Error())
		return
	}
	c.JSON(http.StatusOK, devices)
}
