package cn.sd.jrz.jtool.protocol;

import cn.sd.jrz.jtool.function.ByteConsumer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.RandomAccess;

/**
 * @author JRZ
 * @since 1.0
 */
public class ByteList implements RandomAccess, Cloneable, Serializable {

    /**
     * 默认初始容量。
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 用于空实例的共享空数组实例。
     */
    private static final byte[] EMPTY_ELEMENTDATA = {};

    /**
     * 共享空数组实例，用于默认大小的空实例。
     * 我们将其与空实例的元共享空数组实例区分开来，
     * 以了解添加第一个元素时要填充多少。
     */
    private static final byte[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 存储BitList元素的数组缓冲区。
     * BitList的容量是这个数组缓冲区的长度。
     * 当添加第一个元素时，任何满足 elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * 条件的空 ByteList 都将扩展为 DEFAULT_CAPACITY 大小。
     */
    transient byte[] elementData;

    /**
     * ByteList 的大小（它包含元素的个数）
     *
     * @serial
     */
    private int size;

    /**
     * 构造具有指定初始容量的空列表。
     *
     * @param initialCapacity 列表的初始容量
     * @throws IllegalArgumentException 如果 initialCapacity 不合法
     */
    public ByteList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new byte[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 构造初始容量为10的空列表。
     */
    public ByteList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 按照集合迭代器返回元素的顺序构造包含指定集合的元素的列表。
     *
     * @param c 其元素要放入此列表中的集合
     * @throws NullPointerException 如果指定的集合为空
     */
    public ByteList(ByteList c) {
        elementData = c.toArray();
        if ((size = elementData.length) == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 将此 <tt>ByteList</tt> 实例的容量调整为列表的当前大小。
     * 应用程序可以使用此操作最小化 <tt>ByteList<tt> 实例的存储占用。
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 为了能够容纳 minCapacity 参数指定的个数的元素，
     * 让 <tt>ByteList</tt> 进行必要的扩容。
     *
     * @param minCapacity 最小容量
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private static int calculateCapacity(byte[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        // 内存溢出考虑
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 数组可分配的最大数量
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 为了能够容纳 minCapacity 参数指定的个数的元素，
     * 让 <tt>ByteList</tt> 进行必要的扩容。
     *
     * @param minCapacity 最小容量
     */
    private void grow(int minCapacity) {
        // 对内存溢出进行考虑的代码
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        //
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    /**
     * 返回当前列表中元素的个数
     *
     * @return 当前列表中元素的个数
     */
    public int size() {
        return size;
    }

    /**
     * 如果当前列表不包含元素，将返回 <tt>true</tt>
     *
     * @return <tt>true</tt>，如果当前列表没有元素
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 如果当前列表包含这个特殊的元素，返回 <tt>true</tt>
     *
     * @param bit 用于测试其在该列表中的存在的元素
     * @return <tt>true</tt>，如果当前列表包含这个特殊的元素
     */
    public boolean contains(byte bit) {
        return indexOf(bit) >= 0;
    }

    /**
     * 返回 bit 元素第一次出现的位置，如果未找到，返回 -1
     *
     * @param bit 特殊的元素
     * @return 第一次出现的位置
     */
    public int indexOf(byte bit) {
        for (int i = 0; i < size; i++) {
            if (bit == elementData[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回 bit 元素最后一次出现的位置，如果未找到，返回-1
     *
     * @param bit 特殊的元素
     * @return 最后一次出现的位置
     */
    public int lastIndexOf(byte bit) {
        for (int i = size - 1; i >= 0; i--) {
            if (bit == elementData[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深复制
     *
     * @return 一个 <tt>ByteList</tt> 实例的复制
     */
    @Override
    public Object clone() {
        try {
            ByteList v = (ByteList) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * 返回一个列表中按按序元素的数组
     *
     * @return 列表中按按序元素的数组
     */
    public byte[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public void forEach(ByteConsumer action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i = 0; modCount == expectedModCount && i < size; i++) {
            action.accept(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    protected transient int modCount = 0;
}
