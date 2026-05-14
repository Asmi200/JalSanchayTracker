# 💧 Jal Sanchay Tracker

> A Digital Rainwater Harvesting Tracker for Indian Households

Jal Sanchay Tracker is an Android application that turns rainwater harvesting into a measurable goal. By entering roof area and rainfall data, the app calculates **Water Wealth** — encouraging sustainable water conservation habits at the household level.

---

## 🌧️ Problem Statement

Many households in India have rainwater harvesting systems but no way to track if they are effective. Without data, conservation feels intangible. Jal Sanchay Tracker solves this by making water savings visible, measurable, and meaningful.

---

## ✨ Features

- 🏠 Setup Screen — Enter roof area, tank capacity, and roof type
- 🌧️ Rainfall Logger — Manual entry of daily rainfall in mm with live preview
- 💧 Water Tank Visual — Animated tank that fills up based on data entered
- 📊 Dashboard — Shows Litres Saved Today, Total Savings, and Impact Score
- 🌍 Impact Score — Converts litres into Household Water Days (CPHEEO standard)
- 📅 Monthly Report — Complete history of water saved per month
- 💡 Personalised Tips — Water harvesting tips based on your specific roof type
- 🗑️ Delete Entries — Remove incorrect rainfall entries with confirmation
- 🌧️ Animated Rain Background — Custom canvas animation on the log screen

---

## 🧮 Core Formula

```
Litres Saved = Roof Area (sq ft) × Rainfall (mm) × 0.0929 × Runoff Coefficient
```

| Roof Type | Runoff Coefficient |
|---|---|
| RCC Flat Roof | 0.80 |
| Tiled / Sloped Roof | 0.75 |
| Rough Concrete | 0.60 |
| Gravel / Terrace Garden | 0.50 |

**Impact Score Formula:**
```
Household Water Days = Total Litres Saved ÷ 135
```
> 135L is the CPHEEO (Central Public Health and Environmental Engineering Organisation) standard daily water requirement per person in India.

---

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

```
UI Layer          →    ViewModel Layer    →    Repository Layer    →    Room Database
(Activities,           (WaterWealth            (WaterRepository)        (AppDatabase)
 Fragments)             ViewModel)
```

---

## 🗄️ Database Schema

### UserSetup Table
| Column | Type | Description |
|---|---|---|
| id | Int (PK) | Always 1 (single setup) |
| roofAreaSqFt | Float | Roof area in square feet |
| tankCapacityLitres | Float | Tank capacity in litres |
| runoffCoefficient | Float | Based on roof type |

### RainfallEntry Table
| Column | Type | Description |
|---|---|---|
| id | Int (PK, AutoGenerate) | Unique entry ID |
| date | String | Date in yyyy-MM-dd format |
| rainfallMm | Float | Rainfall in millimetres |
| litersSaved | Float | Calculated litres saved |

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Kotlin** | Primary programming language |
| **MVVM Architecture** | Separation of concerns |
| **Room Database** | Local SQLite storage |
| **LiveData** | Reactive UI updates |
| **ViewModel** | Business logic and lifecycle management |
| **ViewBinding** | Type-safe view references |
| **Kotlin Coroutines** | Background thread operations |
| **Material Design** | UI components |
| **Custom Canvas View** | Animated raindrop background |

---

## 📁 Project Structure

```
com.example.jalsanchaytracker
│
├── 📂 Database
│   ├── UserSetup.kt           — Room entity for setup
│   ├── RainfallEntry.kt       — Room entity for rainfall logs
│   ├── MonthlyTotal.kt        — Data class for monthly report
│   ├── UserSetupDao.kt        — DAO for setup operations
│   ├── RainfallDao.kt         — DAO for rainfall operations
│   └── AppDatabase.kt         — Room database singleton
│
├── 📂 Repository
│   └── WaterRepository.kt     — Single source of truth
│
├── 📂 ViewModel
│   └── WaterWealthViewModel.kt — Business logic and formula
│
├── 📂 UI
│   ├── SetupActivity.kt       — First-time setup screen
│   ├── MainActivity.kt        — Host activity with bottom nav
│   ├── DashboardFragment.kt   — Tank visual and stats
│   ├── LogFragment.kt         — Rainfall entry screen
│   ├── ReportFragment.kt      — Monthly report screen
│   └── TipsFragment.kt        — Personalised tips screen
│
├── 📂 Adapters
│   ├── RainfallEntryAdapter.kt  — RecyclerView for entries
│   ├── MonthlyReportAdapter.kt  — RecyclerView for monthly report
│   └── TipsAdapter.kt           — RecyclerView for tips
│
└── 📂 Custom Views
    ├── RainView.kt              — Animated raindrop canvas
    └── Tip.kt                   — Tip data class
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- Android SDK API 24 or higher
- Kotlin 1.9.22



## 📊 Impact Goals

| Goal | Description |
|---|---|
| 🔒 Water Security | Helps India meet water conservation targets at household level |
| 📚 Sustainability Education | Makes concepts like Runoff and Catchment real for citizens |
| 📋 Resource Management | Data-driven gardening and household usage planning |

---

## ✅ Success Criteria

- ✅ Water tank visual fills up relative to data entered
- ✅ Monthly report of total water saved
- ✅ Math calculations validated with graceful error handling
- ✅ Tips section with personalised roof-type based recommendations
- ✅ Input validation for non-numeric and out-of-range values

---

## 🧑‍💻 Developer

**Built as part of VTU Internship Project**
---

"Water is the driving force of all nature." — Leonardo da Vinci_ 💧
