package data.repository

interface AuthRepository {
    suspend fun authenticate(email: String, password: String): Boolean
}