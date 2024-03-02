package device

import (
	"backend/internal/device/handlers"
	"backend/internal/device/repositories"
	"backend/internal/device/services"
	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

func Device(router *gin.RouterGroup, db *gorm.DB) {
	deviceRepository := repositories.NewDeviceRepository(db)
	deviceService := services.NewDeviceService(deviceRepository)
	deviceHandler := handlers.NewDeviceHandler(deviceService)

	router.GET("/device", deviceHandler.GetAll)
	router.GET("/device/:id", deviceHandler.GetById)
}
