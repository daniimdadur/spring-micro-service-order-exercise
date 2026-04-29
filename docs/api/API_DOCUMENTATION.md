# API Documentation - Spring Micro Service Order Exercise

## Overview
API dokumentasi untuk Spring Micro Service Order Exercise yang menyediakan endpoint untuk mengelola Customer, Category, Product, Order, dan Order Details.

## Base URL
```
http://localhost:8080/v1/api
```

## Server Configuration
- **Database**: MySQL (localhost:3306)
- **Database Name**: micro_service_order
- **Cache**: Redis (localhost:6379)
- **Username**: root
- **Password**: P@ssW0rd32!

---

## API Endpoints

### 1. Customer API

#### 1.1 Get All Customers
**Endpoint:** `GET /customer`

**Description:** Mengambil semua data customer

**Request:**
```
GET /v1/api/customer
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "abc123def456",
      "name": "John Doe",
      "email": "john@example.com",
      "phone": "08123456789",
      "address": "Jl. Merdeka No. 1",
      "createdAt": "2026-04-29T10:30:00",
      "updatedAt": "2026-04-29T10:30:00"
    }
  ]
}
```

---

#### 1.2 Get Customer By ID
**Endpoint:** `GET /customer/{id}`

**Description:** Mengambil data customer berdasarkan ID

**Request:**
```
GET /v1/api/customer/abc123def456
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "abc123def456",
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "08123456789",
    "address": "Jl. Merdeka No. 1",
    "createdAt": "2026-04-29T10:30:00",
    "updatedAt": "2026-04-29T10:30:00"
  }
}
```

**Response (Not Found):**
```json
{
  "status": 404,
  "message": "Not Found",
  "data": null
}
```

---

#### 1.3 Create Customer
**Endpoint:** `POST /customer`

**Description:** Membuat data customer baru

**Request:**
```json
POST /v1/api/customer
Content-Type: application/json

{
  "name": "Jane Smith",
  "email": "jane@example.com",
  "phone": "08987654321",
  "address": "Jl. Sudirman No. 50"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "xyz789abc123",
    "name": "Jane Smith",
    "email": "jane@example.com",
    "phone": "08987654321",
    "address": "Jl. Sudirman No. 50",
    "createdAt": "2026-04-29T11:00:00",
    "updatedAt": "2026-04-29T11:00:00"
  }
}
```

---

#### 1.4 Update Customer
**Endpoint:** `PUT /customer/{id}`

**Description:** Mengupdate data customer

**Request:**
```json
PUT /v1/api/customer/abc123def456
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "phone": "08111111111",
  "address": "Jl. Merdeka No. 2"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "abc123def456",
    "name": "John Updated",
    "email": "john.updated@example.com",
    "phone": "08111111111",
    "address": "Jl. Merdeka No. 2",
    "createdAt": "2026-04-29T10:30:00",
    "updatedAt": "2026-04-29T11:30:00"
  }
}
```

---

#### 1.5 Delete Customer
**Endpoint:** `DELETE /customer/{id}`

**Description:** Menghapus data customer (Soft Delete)

**Request:**
```
DELETE /v1/api/customer/abc123def456
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "abc123def456",
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "08123456789",
    "address": "Jl. Merdeka No. 1",
    "createdAt": "2026-04-29T10:30:00",
    "updatedAt": "2026-04-29T10:30:00"
  }
}
```

---

### 2. Category API

#### 2.1 Get All Categories
**Endpoint:** `GET /category`

**Description:** Mengambil semua data kategori

**Request:**
```
GET /v1/api/category
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "cat001",
      "name": "Electronics",
      "description": "Electronic Items",
      "createdAt": "2026-04-29T09:00:00"
    },
    {
      "id": "cat002",
      "name": "Clothing",
      "description": "Clothing Items",
      "createdAt": "2026-04-29T09:15:00"
    }
  ]
}
```

---

#### 2.2 Get Category By ID
**Endpoint:** `GET /category/{id}`

**Description:** Mengambil data kategori berdasarkan ID

**Request:**
```
GET /v1/api/category/cat001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "cat001",
    "name": "Electronics",
    "description": "Electronic Items",
    "createdAt": "2026-04-29T09:00:00"
  }
}
```

---

#### 2.3 Create Category
**Endpoint:** `POST /category`

**Description:** Membuat data kategori baru

**Request:**
```json
POST /v1/api/category
Content-Type: application/json

{
  "name": "Books",
  "description": "Books and Publications"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "cat003",
    "name": "Books",
    "description": "Books and Publications",
    "createdAt": "2026-04-29T09:30:00"
  }
}
```

---

#### 2.4 Update Category
**Endpoint:** `PUT /category/{id}`

**Description:** Mengupdate data kategori

**Request:**
```json
PUT /v1/api/category/cat001
Content-Type: application/json

{
  "name": "Electronics & Gadgets",
  "description": "Updated Electronic Items"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "cat001",
    "name": "Electronics & Gadgets",
    "description": "Updated Electronic Items",
    "createdAt": "2026-04-29T09:00:00"
  }
}
```

---

#### 2.5 Delete Category
**Endpoint:** `DELETE /category/{id}`

**Description:** Menghapus data kategori (Soft Delete)

**Request:**
```
DELETE /v1/api/category/cat003
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "cat003",
    "name": "Books",
    "description": "Books and Publications",
    "createdAt": "2026-04-29T09:30:00"
  }
}
```

---

### 3. Product API

#### 3.1 Get All Products
**Endpoint:** `GET /product`

**Description:** Mengambil semua data produk

**Request:**
```
GET /v1/api/product
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "prod001",
      "name": "Laptop",
      "description": "Dell XPS 13",
      "price": 1500.00,
      "stock": 10,
      "sku": "DELL-XPS-13",
      "categoryId": "cat001",
      "createdAt": "2026-04-29T10:00:00",
      "updatedAt": "2026-04-29T10:00:00"
    }
  ]
}
```

---

#### 3.2 Get Product By ID
**Endpoint:** `GET /product/{id}`

**Description:** Mengambil data produk berdasarkan ID

**Request:**
```
GET /v1/api/product/prod001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "prod001",
    "name": "Laptop",
    "description": "Dell XPS 13",
    "price": 1500.00,
    "stock": 10,
    "sku": "DELL-XPS-13",
    "categoryId": "cat001",
    "createdAt": "2026-04-29T10:00:00",
    "updatedAt": "2026-04-29T10:00:00"
  }
}
```

---

#### 3.3 Create Product
**Endpoint:** `POST /product`

**Description:** Membuat data produk baru

**Request:**
```json
POST /v1/api/product
Content-Type: application/json

{
  "name": "iPhone 15",
  "description": "Apple iPhone 15 Pro",
  "price": 999.99,
  "stock": 25,
  "sku": "IPHONE-15-PRO",
  "categoryId": "cat001"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "prod002",
    "name": "iPhone 15",
    "description": "Apple iPhone 15 Pro",
    "price": 999.99,
    "stock": 25,
    "sku": "IPHONE-15-PRO",
    "categoryId": "cat001",
    "createdAt": "2026-04-29T10:30:00",
    "updatedAt": "2026-04-29T10:30:00"
  }
}
```

---

#### 3.4 Update Product
**Endpoint:** `PUT /product/{id}`

**Description:** Mengupdate data produk

**Request:**
```json
PUT /v1/api/product/prod001
Content-Type: application/json

{
  "name": "Laptop Updated",
  "description": "Dell XPS 13 Plus",
  "price": 1600.00,
  "stock": 15,
  "sku": "DELL-XPS-13-PLUS",
  "categoryId": "cat001"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "prod001",
    "name": "Laptop Updated",
    "description": "Dell XPS 13 Plus",
    "price": 1600.00,
    "stock": 15,
    "sku": "DELL-XPS-13-PLUS",
    "categoryId": "cat001",
    "createdAt": "2026-04-29T10:00:00",
    "updatedAt": "2026-04-29T11:00:00"
  }
}
```

---

#### 3.5 Delete Product
**Endpoint:** `DELETE /product/{id}`

**Description:** Menghapus data produk (Soft Delete)

**Request:**
```
DELETE /v1/api/product/prod002
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "prod002",
    "name": "iPhone 15",
    "description": "Apple iPhone 15 Pro",
    "price": 999.99,
    "stock": 25,
    "sku": "IPHONE-15-PRO",
    "categoryId": "cat001",
    "createdAt": "2026-04-29T10:30:00",
    "updatedAt": "2026-04-29T10:30:00"
  }
}
```

---

#### 3.6 Get Products By Category
**Endpoint:** `GET /product/category/{categoryId}`

**Description:** Mengambil semua produk berdasarkan kategori

**Request:**
```
GET /v1/api/product/category/cat001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "prod001",
      "name": "Laptop",
      "description": "Dell XPS 13",
      "price": 1500.00,
      "stock": 10,
      "sku": "DELL-XPS-13",
      "categoryId": "cat001",
      "createdAt": "2026-04-29T10:00:00",
      "updatedAt": "2026-04-29T10:00:00"
    }
  ]
}
```

---

### 4. Order API

#### 4.1 Get All Orders
**Endpoint:** `GET /order`

**Description:** Mengambil semua data order

**Request:**
```
GET /v1/api/order
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "ord001",
      "orderNumber": "ORD-001",
      "customerId": "abc123def456",
      "orderDate": "2026-04-29T12:00:00",
      "status": "PENDING",
      "paymentStatus": "UNPAID",
      "paymentId": null,
      "totalAmount": 2499.99,
      "paymentMethod": "CREDIT_CARD",
      "paidAt": null,
      "notes": "Urgent delivery",
      "createdAt": "2026-04-29T12:00:00"
    }
  ]
}
```

---

#### 4.2 Get Order By ID
**Endpoint:** `GET /order/{id}`

**Description:** Mengambil data order berdasarkan ID

**Request:**
```
GET /v1/api/order/ord001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "ord001",
    "orderNumber": "ORD-001",
    "customerId": "abc123def456",
    "orderDate": "2026-04-29T12:00:00",
    "status": "PENDING",
    "paymentStatus": "UNPAID",
    "paymentId": null,
    "totalAmount": 2499.99,
    "paymentMethod": "CREDIT_CARD",
    "paidAt": null,
    "notes": "Urgent delivery",
    "createdAt": "2026-04-29T12:00:00"
  }
}
```

---

#### 4.3 Create Order
**Endpoint:** `POST /order`

**Description:** Membuat data order baru

**Request:**
```json
POST /v1/api/order
Content-Type: application/json

{
  "orderNumber": "ORD-002",
  "customerId": "abc123def456",
  "orderDate": "2026-04-29T13:00:00",
  "status": "PENDING",
  "paymentStatus": "UNPAID",
  "totalAmount": 1999.99,
  "paymentMethod": "TRANSFER",
  "notes": "Express delivery"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "ord002",
    "orderNumber": "ORD-002",
    "customerId": "abc123def456",
    "orderDate": "2026-04-29T13:00:00",
    "status": "PENDING",
    "paymentStatus": "UNPAID",
    "paymentId": null,
    "totalAmount": 1999.99,
    "paymentMethod": "TRANSFER",
    "paidAt": null,
    "notes": "Express delivery",
    "createdAt": "2026-04-29T13:00:00"
  }
}
```

---

#### 4.4 Update Order
**Endpoint:** `PUT /order/{id}`

**Description:** Mengupdate data order

**Request:**
```json
PUT /v1/api/order/ord001
Content-Type: application/json

{
  "orderNumber": "ORD-001",
  "customerId": "abc123def456",
  "orderDate": "2026-04-29T12:00:00",
  "status": "CONFIRMED",
  "paymentStatus": "PAID",
  "paymentId": "PAY-12345",
  "totalAmount": 2499.99,
  "paymentMethod": "CREDIT_CARD",
  "paidAt": "2026-04-29T13:00:00",
  "notes": "Urgent delivery - Updated"
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "ord001",
    "orderNumber": "ORD-001",
    "customerId": "abc123def456",
    "orderDate": "2026-04-29T12:00:00",
    "status": "CONFIRMED",
    "paymentStatus": "PAID",
    "paymentId": "PAY-12345",
    "totalAmount": 2499.99,
    "paymentMethod": "CREDIT_CARD",
    "paidAt": "2026-04-29T13:00:00",
    "notes": "Urgent delivery - Updated",
    "createdAt": "2026-04-29T12:00:00"
  }
}
```

---

#### 4.5 Delete Order
**Endpoint:** `DELETE /order/{id}`

**Description:** Menghapus data order (Soft Delete)

**Request:**
```
DELETE /v1/api/order/ord002
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "ord002",
    "orderNumber": "ORD-002",
    "customerId": "abc123def456",
    "orderDate": "2026-04-29T13:00:00",
    "status": "PENDING",
    "paymentStatus": "UNPAID",
    "paymentId": null,
    "totalAmount": 1999.99,
    "paymentMethod": "TRANSFER",
    "paidAt": null,
    "notes": "Express delivery",
    "createdAt": "2026-04-29T13:00:00"
  }
}
```

---

#### 4.6 Get Orders By Customer
**Endpoint:** `GET /order/customer/{customerId}`

**Description:** Mengambil semua order dari customer tertentu

**Request:**
```
GET /v1/api/order/customer/abc123def456
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "ord001",
      "orderNumber": "ORD-001",
      "customerId": "abc123def456",
      "orderDate": "2026-04-29T12:00:00",
      "status": "PENDING",
      "paymentStatus": "UNPAID",
      "paymentId": null,
      "totalAmount": 2499.99,
      "paymentMethod": "CREDIT_CARD",
      "paidAt": null,
      "notes": "Urgent delivery",
      "createdAt": "2026-04-29T12:00:00"
    }
  ]
}
```

---

#### 4.7 Get Orders By Status
**Endpoint:** `GET /order/status/{status}`

**Description:** Mengambil semua order berdasarkan status

**Request:**
```
GET /v1/api/order/status/PENDING
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "ord001",
      "orderNumber": "ORD-001",
      "customerId": "abc123def456",
      "orderDate": "2026-04-29T12:00:00",
      "status": "PENDING",
      "paymentStatus": "UNPAID",
      "paymentId": null,
      "totalAmount": 2499.99,
      "paymentMethod": "CREDIT_CARD",
      "paidAt": null,
      "notes": "Urgent delivery",
      "createdAt": "2026-04-29T12:00:00"
    }
  ]
}
```

---

### 5. Order Details API

#### 5.1 Get All Order Details
**Endpoint:** `GET /order-details`

**Description:** Mengambil semua data order details

**Request:**
```
GET /v1/api/order-details
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "od001",
      "orderId": "ord001",
      "productId": "prod001",
      "quantity": 2,
      "unitPrice": 1500.00,
      "subtotal": 3000.00
    }
  ]
}
```

---

#### 5.2 Get Order Details By ID
**Endpoint:** `GET /order-details/{id}`

**Description:** Mengambil data order details berdasarkan ID

**Request:**
```
GET /v1/api/order-details/od001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "od001",
    "orderId": "ord001",
    "productId": "prod001",
    "quantity": 2,
    "unitPrice": 1500.00,
    "subtotal": 3000.00
  }
}
```

---

#### 5.3 Create Order Details
**Endpoint:** `POST /order-details`

**Description:** Membuat data order details baru

**Request:**
```json
POST /v1/api/order-details
Content-Type: application/json

{
  "orderId": "ord001",
  "productId": "prod002",
  "quantity": 1,
  "unitPrice": 999.99,
  "subtotal": 999.99
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "od002",
    "orderId": "ord001",
    "productId": "prod002",
    "quantity": 1,
    "unitPrice": 999.99,
    "subtotal": 999.99
  }
}
```

---

#### 5.4 Update Order Details
**Endpoint:** `PUT /order-details/{id}`

**Description:** Mengupdate data order details

**Request:**
```json
PUT /v1/api/order-details/od001
Content-Type: application/json

{
  "orderId": "ord001",
  "productId": "prod001",
  "quantity": 3,
  "unitPrice": 1500.00,
  "subtotal": 4500.00
}
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "od001",
    "orderId": "ord001",
    "productId": "prod001",
    "quantity": 3,
    "unitPrice": 1500.00,
    "subtotal": 4500.00
  }
}
```

---

#### 5.5 Delete Order Details
**Endpoint:** `DELETE /order-details/{id}`

**Description:** Menghapus data order details

**Request:**
```
DELETE /v1/api/order-details/od002
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": {
    "id": "od002",
    "orderId": "ord001",
    "productId": "prod002",
    "quantity": 1,
    "unitPrice": 999.99,
    "subtotal": 999.99
  }
}
```

---

#### 5.6 Get Order Details By Order
**Endpoint:** `GET /order-details/order/{orderId}`

**Description:** Mengambil semua order details dari order tertentu

**Request:**
```
GET /v1/api/order-details/order/ord001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "od001",
      "orderId": "ord001",
      "productId": "prod001",
      "quantity": 2,
      "unitPrice": 1500.00,
      "subtotal": 3000.00
    },
    {
      "id": "od002",
      "orderId": "ord001",
      "productId": "prod002",
      "quantity": 1,
      "unitPrice": 999.99,
      "subtotal": 999.99
    }
  ]
}
```

---

#### 5.7 Get Order Details By Product
**Endpoint:** `GET /order-details/product/{productId}`

**Description:** Mengambil semua order details dari product tertentu

**Request:**
```
GET /v1/api/order-details/product/prod001
```

**Response (Success):**
```json
{
  "status": 200,
  "message": "OK",
  "data": [
    {
      "id": "od001",
      "orderId": "ord001",
      "productId": "prod001",
      "quantity": 2,
      "unitPrice": 1500.00,
      "subtotal": 3000.00
    }
  ]
}
```

---

## Error Response Format

Semua error response mengikuti format berikut:

```json
{
  "status": 404,
  "message": "Not Found",
  "data": null
}
```

---

## HTTP Status Codes

| Status Code | Description |
|-------------|-------------|
| 200 | OK - Request berhasil |
| 400 | Bad Request - Invalid request data |
| 404 | Not Found - Resource tidak ditemukan |
| 500 | Internal Server Error - Server error |

---

## Setup & Running

### Prerequisite
- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### Start Services
```bash
docker-compose up -d
```

### Build Project
```bash
mvn clean install
```

### Run Application
```bash
mvn spring-boot:run
```

Aplikasi akan berjalan di `http://localhost:8080`

---

## Database Schema

Database menggunakan MySQL dengan nama `micro_service_order`.

### Tables:
- **t_customer**: Menyimpan data customer
- **t_category**: Menyimpan data kategori
- **t_product**: Menyimpan data produk
- **t_order**: Menyimpan data order
- **t_order_details**: Menyimpan detail item dalam order

Semua table menggunakan soft delete dengan field `deleted_at`.

---

## Features

✅ CRUD Operations untuk semua entity
✅ Relationship management (One-to-Many, Many-to-One)
✅ Soft Delete implementation
✅ Audit fields (createdAt, updatedAt)
✅ UUID Auto-generation untuk ID
✅ Error handling dengan custom exceptions
✅ Redis caching support
✅ MySQL database integration
✅ Docker Compose setup

---

## Notes

- Semua ID di-generate secara otomatis menggunakan UUID (32 character hex string)
- Delete operation menggunakan soft delete - data tetap ada di database dengan field `deleted_at`
- Relationship antar entity di-manage melalui foreign keys
- Response format konsisten untuk semua endpoint


