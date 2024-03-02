package entities

type Device struct {
	ID             uint64 `gorm:"primaryKey;autoIncrement;not null;unique;column:id" json:"id"`
	Type           string `gorm:"not null;column:type" json:"type"`
	Brand          string `gorm:"not null;column:brand" json:"brand"`
	Model          string `gorm:"not null;column:model" json:"model"`
	Video          bool   `gorm:"not null;column:video" json:"video"`
	WiFi           bool   `gorm:"not null;column:wifi" json:"wifi"`
	Ghz24          bool   `gorm:"not null;column:ghz24" json:"ghz24"`
	Ghz5           bool   `gorm:"not null;column:ghz5" json:"ghz5"`
	Protocol       string `gorm:"column:protocol" json:"protocol"`
	PrivacyShutter bool   `gorm:"column:privacy_shutter" json:"privacy_shutter"`
	Encryption     string `gorm:"column:encryption" json:"encryption"`
	IsSecure       bool   `gorm:"column:is_secure" json:"is_secure"`
	InfoLink       string `gorm:"column:info_link" json:"info_link"`
	Comments       string `gorm:"column:comments" json:"comments"`
}

func (Device) TableName() string {
	return "devices"
}
