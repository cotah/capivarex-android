# CAPIVAREX Android

Native Android app for CAPIVAREX — Your AI Life Assistant.

## Stack

- **Language:** Kotlin 2.1
- **UI:** Jetpack Compose + Material 3
- **Architecture:** MVVM + Hilt DI
- **Network:** Retrofit + OkHttp + Kotlinx Serialization
- **Auth:** Supabase Auth + DataStore
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 35

## Setup

### 1. Clone
```bash
git clone https://github.com/cotah/capivarex-android.git
cd capivarex-android
```

### 2. Configure Supabase credentials
Create or edit `local.properties` in the project root:
```properties
SUPABASE_URL=https://your-project.supabase.co
SUPABASE_ANON_KEY=your-anon-key
```

### 3. Open in Android Studio
- Open Android Studio → Open → select the project root
- Wait for Gradle sync to finish
- Select an emulator or connected device
- Click ▶ Run

## Architecture

```
app/src/main/java/com/capivarex/android/
├── data/
│   ├── api/          # Retrofit API service
│   ├── auth/         # Supabase auth + token management
│   ├── model/        # Data classes (User, Chat, etc.)
│   └── repository/   # Data layer (future)
├── di/               # Hilt dependency injection
├── ui/
│   ├── auth/         # Login + Register screens
│   ├── chat/         # Chat with ARA
│   ├── services/     # Capivaras grid
│   ├── notes/        # Notes (placeholder)
│   ├── settings/     # Profile, plan, logout
│   ├── components/   # Shared UI components
│   ├── navigation/   # Routes + bottom nav
│   └── theme/        # Colors, typography (dark + gold)
├── CapivarexApp.kt   # Hilt Application
└── MainActivity.kt   # Entry point
```

## Backend

The app connects to the existing CAPIVAREX backend:
- **Production:** `https://capivarex-production.up.railway.app`
- Same REST API used by the web frontend
- Auth via Supabase JWT tokens

## Design

- Dark theme (#0A0A0A void black)
- Gold accent (#D4A017)
- Glass morphism cards
- Material 3 components

## Screens

| Screen | Status | Description |
|--------|--------|-------------|
| Login | ✅ | Email + password via Supabase |
| Register | ✅ | Name + email + password |
| Chat | ✅ | Full chat with ARA |
| Services | ✅ | Capivaras grid |
| Notes | 🏗️ | Placeholder (UI ready) |
| Settings | ✅ | Profile, plan, usage, logout |
