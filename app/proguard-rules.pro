# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.capivarex.android.data.model.** { *; }

# Kotlinx Serialization
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class kotlinx.serialization.json.** { kotlinx.serialization.KSerializer serializer(...); }
-keep,includedescriptorclasses class com.capivarex.android.data.model.**$$serializer { *; }
