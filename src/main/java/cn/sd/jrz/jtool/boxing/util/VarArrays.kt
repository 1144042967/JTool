package cn.sd.jrz.jtool.boxing.util

import cn.sd.jrz.jtool.boxing.primitive.*
import java.math.BigDecimal
import java.util.*

fun asVarList(vararg a: Boolean) = ArrayList(a.map { VarBoolean(it) })
fun asVarList(vararg a: Byte) = ArrayList(a.map { VarByte(it) })
fun asVarList(vararg a: Short) = ArrayList(a.map { VarShort(it) })
fun asVarList(vararg a: Int) = ArrayList(a.map { VarInt(it) })
fun asVarList(vararg a: Long) = ArrayList(a.map { VarLong(it) })
fun asVarList(vararg a: Float) = ArrayList(a.map { VarFloat(it) })
fun asVarList(vararg a: Double) = ArrayList(a.map { VarDouble(it) })
fun asVarList(vararg a: BigDecimal) = ArrayList(a.map { VarDecimal(it) })