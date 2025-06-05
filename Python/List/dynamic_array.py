import ctypes
from typing import Generic,TypeVar

T = TypeVar('T') #Generic type

def Static_Array(capacity: int):
    return (ctypes.py_object * capacity)()

def ThrowException(message: str = "Index out of bounds") -> str:
        raise ValueError(message)

class DynamicArray(Generic[T]):
    def __init__(self, capacity: int = 10):
        self.__size__: int = 0 #number of element
        self.__capacity__: int = capacity
        self.__array__ = Static_Array(capacity) #Generate static array based on capacity value

    def add(self, element: T) -> T:
        if self.__size__ >= self.__capacity__ - 1:
            if(self.__capacity__ == 0): 
                self.__capacity__ = 1
            else:
                self.__capacity__ *= 2 #New capacity

            new_array = Static_Array(self.__capacity__) #New static array with double slots

            for i in range(len(self.__array__)): #copy old array to new array
                new_array[i] = self.__array__[i]
            
            self.__array__ = new_array
        else:
            self.__size__+= 1
            self.__array__[self.__size__] = element
    
    def removeAt(self, remove_index: int):
        if(remove_index > self.size() | remove_index < 0):
            ThrowException()

        new_array = Static_Array(self.size - 1)
        new_index = 0

        for i in range(len(self.__array__)):
            if i == remove_index:
                continue
            new_array[new_index] = self.__array__[i]
            new_index += 1     

        self.__array__ = new_array
        self.__size__ -= 1 
        self.__capacity__ -= 1

    def size(self) -> int:
        return self.__size__
    
    def is_empty(self) -> bool:
        return self.size() == 0
    
    def get(self, index: int) -> T:
       if not 0 <= index < self.size:
           ThrowException()
       return self.__array__[index]
    
    def set(self,index: int, element: T):
        self.__array__[index] = element

    def clear(self):
        for i in range(self.__array__):
            self.__array__[i] = None
        self.__size__ = 0
