package net.corda.training.state

import net.corda.core.contracts.*
import net.corda.core.identity.Party
import net.corda.training.contract.IOUContract
import java.util.*;
/**
 * The IOU State object, with the following properties:
 *
 * - [amount] The amount owed by the [borrower] to the [lender]
 *
 * Remove the "val data: String = "data" property before starting the [IOUState] tasks.
 */
@BelongsToContract(IOUContract::class)
data class IOUState(val amount:Amount<Currency>, val lender:Party, val borrower:Party,val paid:Amount<Currency> = Amount(0, amount.token),override val linearId: UniqueIdentifier = UniqueIdentifier()): LinearState {
    override val participants: List<Party> get() = listOf(lender,borrower)

    fun pay(amountToPay: Amount<Currency>) = copy(paid = paid.plus(amountToPay))

    fun withNewLender(newLender: Party) = copy(lender = newLender)

}