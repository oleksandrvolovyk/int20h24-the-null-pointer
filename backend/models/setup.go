package models

import (
	"backend/packages/models/entities"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"os"
)

func ConnectDatabase() (*gorm.DB, error) {
	// Initialize the database
	database, err := gorm.Open(postgres.Open(os.Getenv("DB_DSN")), &gorm.Config{})
	err = database.AutoMigrate(&entities.Device{})
	if err != nil {
		return nil, err
	}
	return database, err
}
