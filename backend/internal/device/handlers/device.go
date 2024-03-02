package handlers

import (
	"backend/models/entities"
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

func (g *DeviceHandler) GetById(c *gin.Context) {
	// Get the ID from the URL parameter
	id := c.Param("id")

	device, err := g.deviceService.GetDeviceById(id)
	if err != nil {
		c.IndentedJSON(http.StatusNotFound, err.Error())
		return
	}
	c.JSON(http.StatusOK, device)
}
