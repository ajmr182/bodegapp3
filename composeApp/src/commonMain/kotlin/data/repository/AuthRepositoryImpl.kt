package data.repository

import data.SupabaseSetup
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email

public class AuthRepositoryImpl(private val supabaseClient: SupabaseSetup) : AuthRepository {
    override suspend fun authenticate(email: String, password: String): Boolean {
        return runCatching{
            supabaseClient.client.gotrue.loginWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } .getOrElse {
            false
        }
    }
}