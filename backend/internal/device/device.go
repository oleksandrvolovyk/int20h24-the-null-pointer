package device

import (
	"backend/packages/internal/device/handlers"
	"backend/packages/internal/device/repositories"
	"backend/packages/internal/device/services"
	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

func Device(router *gin.RouterGroup, db *gorm.DB) {
	deviceRepository := repositories.NewDeviceRepository(db)
	deviceService := services.NewDeviceService(deviceRepository)
	deviceHandler := handlers.NewDeviceHandler(deviceService)

	router.GET("/device", deviceHandler.GetAll)
}
