package data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

class SupabaseSetup {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://lebamklezdwtmsxrdyzn.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxlYmFta2xlemR3dG1zeHJkeXpuIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTUxNjQ4NzEsImV4cCI6MjAxMDc0MDg3MX0.805xwZ_gNFA1f263SgF9J8phHA4UGi-U8yR-4b98v0M"
    ) {
        install(GoTrue)
        install(Postgrest)
    }
}