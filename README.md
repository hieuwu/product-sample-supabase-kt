# 🚀 Product Sample Supabase

[![Made with Supabase](https://supabase.com/badge-made-with-supabase-dark.svg)](https://supabase.com)

![Product Sample Cover](https://github.com/hieuwu/product-sample-supabase-kt/assets/43868345/1bed0c84-208a-4266-a2ec-2bac50ddf80c)

## 📖 Overview

This project is a modern Android application built with **Jetpack Compose**, showcasing **best practices** for integrating **Supabase** for **authentication**, **OAuth**, **storage**, and **real-time CRUD operations**. It serves as a reference for developers aiming to build scalable, maintainable Android apps with a robust backend.

### 🎯 Features
- **Supabase Integration**: Email/password authentication, OAuth (e.g., Google), file storage, and real-time database operations.
- **Jetpack Compose**: Declarative UI for a responsive and modern user experience.
- **Hilt Dependency Injection**: Clean architecture for modularity and testability.
- **Real-time Data**: Leverages Supabase's real-time subscriptions for live updates.
- **CI/CD**: Automated builds and testing via GitHub Actions.
- **Image Loading**: Efficient image handling with Coil.

## 🛠️ Setup

### Prerequisites
- **Android Studio**: SDK 30 (Android 11) or higher.
- **Supabase Account**: Obtain API key, secret, and project URL from [Supabase](https://supabase.com).
- **Kotlin**: Version 1.9.0 or higher recommended.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/hieuwu/product-sample-supabase-kt.git
   ```
2. Open the project in Android Studio.
3. Create or update the `local.properties` file in the project root:
   ```kotlin
   API_KEY=YOUR_SUPABASE_API_KEY
   SECRET=YOUR_SUPABASE_SECRET
   SUPABASE_URL=YOUR_SUPABASE_URL
   ```
   Replace `YOUR_SUPABASE_API_KEY`, `YOUR_SUPABASE_SECRET`, and `YOUR_SUPABASE_URL` with values from your Supabase project dashboard.
4. Sync the project with Gradle and build the app.
