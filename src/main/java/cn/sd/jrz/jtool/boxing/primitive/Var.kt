package cn.sd.jrz.jtool.boxing.primitive

import java.math.BigDecimal

/**
 * @author 江荣展
 * @date 2020/7/1
 */
abstract class Var<T>(open var value: T) {
    override fun toString(): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        return other is Var<*> && value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

class VarBoolean(override var value: Boolean = false) : Var<Boolean>(value)
class VarByte(override var value: Byte = 0) : Var<Byte>(value)
class VarShort(override var value: Short = 0) : Var<Short>(value)
class VarInt(override var value: Int = 0) : Var<Int>(value)
class VarLong(override var value: Long = 0) : Var<Long>(value)
class VarFloat(override var value: Float = 0F) : Var<Float>(value)
class VarDouble(override var value: Double = 0.0) : Var<Double>(value)
class VarDecimal(override var value: BigDecimal = BigDecimal(0)) : Var<BigDecimal>(value)
class VarAny(override var value: Any? = null) : Var<Any?>(value)
