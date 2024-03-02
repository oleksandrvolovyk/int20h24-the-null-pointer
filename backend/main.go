package main

import (
	"backend/internal/device"
	"backend/models"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
	"log"
	"net/http"
	"os"
)

func main() {
	// Load env variables from file
	err := godotenv.Load("credentials.env")
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	// Initialize the database
	db, err := models.ConnectDatabase()
	if err != nil {
		panic(err)
	}

	// Create a new Gin router
	server := gin.Default()

	server.ForwardedByClientIP = true
	err = server.SetTrustedProxies([]string{"127.0.0.1"})
	if err != nil {
		log.Fatal("Error setting trusted proxies")
	}

	config := cors.DefaultConfig()
	config.AllowAllOrigins = true
	server.Use(cors.New(config))

	router := server.Group("")
	router.GET("/", func(c *gin.Context) {
		c.String(http.StatusOK, "Hello")
	})
	apiRouter := router.Group("/api/v1")
	device.Device(apiRouter, db)

	port := os.Getenv("PORT")

	if port == "" {
		port = "80"
	}

	// Start the server
	log.Println("Starting server on port " + port)
	err = server.Run(":" + port)
	if err != nil {
		log.Fatal(err)
	}
}
