package kr.peerfund.user.repository

import kr.peerfund.user.model.User
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class UserRepository(
    private val userJpaRepository: UserJpaRepository
) : QuerydslRepositorySupport(User::class.java), UserJpaRepository by userJpaRepository {

    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}
