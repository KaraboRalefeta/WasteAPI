# RecyclingAPI

## Overview
The Item Repository API provides information about recycling, including details on specific items, their impact, recycling and reuse instructions, and general recycling tips. The API also allows users to manage categories (materials) and retrieve random or specific tips.

## Base URL
`http://localhost:8080/`

---

## Endpoints

### 1. Item Repository
#### **1.1 Add an Item**
- **Endpoint:** `POST /recycle/add`
- **Request Body (JSON):**
  ```json
  {
    "name": "Plastic Bottle",
    "Categ_ID": "plastic",
    "impact": "High carbon footprint",
    "recycling_Instr": "Rinse before recycling",
    "recyclable": true,
    "reuse_Instr": "Can be repurposed for storage"
  }
  ```

#### **1.2 Get a Specific Item**
- **By ID:** `GET /recycle/item?id={id}`
- **By Name:** `GET /recycle/item?name={name}` (Returns a list if multiple items have the same name)
- **Example Response:**
  ```json
  {
    "status": 200,
    "body": {
      "id": 1,
      "name": "Plastic Bottle",
      "Categ_ID": "plastic",
      "impact": "High carbon footprint",
      "recycling_Instr": "Rinse before recycling",
      "recyclable": true,
      "reuse_Instr": "Can be repurposed for storage"
    }
  }
  ```

#### **1.3 Get a Random Item**
- **Endpoint:** `GET /recycle/item`

#### **1.4 Delete an Item**
- **Endpoint:** `DELETE /recycle/delete/{id}`

#### **1.5 Edit an Item**
- **Endpoint:** `PUT /recycle/edit/{id}`
- **Request Body (JSON):** (Include only the fields to be updated)
  ```json
  {
    "name": "Glass Bottle",
    "recyclable": true
  }
  ```

#### **1.6 Get All Items**
- **Endpoint:** `GET /recycle/item/all`

---

### 2. Categories (Materials)
#### **2.1 Add a Category**
- **Endpoint:** `POST /category/add`
- **Request Body (JSON):**
  ```json
  {
    "name": "plastic"
  }
  ```

#### **2.2 Get a Category**
- **By Name:** `GET /category?name={name}`

#### **2.3 Delete a Category**
- **Endpoint:** `DELETE /category/delete/{id}`

#### **2.4 Edit a Category**
- **Endpoint:** `PUT /category/edit/{id}`

#### **2.5 Get All Categories**
- **Endpoint:** `GET /category/all`

---

### 3. Tips
#### **3.1 Add a Tip**
- **Endpoint:** `POST /tip/add`
- **Request Body (JSON):**
  ```json
  {
    "tip": "Use cloth bags instead of plastic bags."
  }
  ```

#### **3.2 Get a Tip**
- **Endpoint:** `GET /tip/{id}`

#### **3.3 Delete a Tip**
- **Endpoint:** `DELETE /tip/delete/{id}`

#### **3.4 Edit a Tip**
- **Endpoint:** `PUT /tip/edit/{id}`

#### **3.5 Get All Tips**
- **Endpoint:** `GET /tip/all`

---

## Response Format
All responses follow a consistent format:

### **Success Response**
```json
{
  "status": 200,
  "body": { ... }
}
```

### **Error Response**
```json
{
  "status": 400,
  "body": {
    "errorMessage": "Invalid request format."
  }
}
```

---

## Purpose
This API is designed to provide information on recycling, including:
- How to recycle specific items
- The impact of materials
- General recycling tips
- Information on recyclable materials

Use this API to enhance sustainability applications or provide users with guidance on eco-friendly practices.

