package com.elewa.marvelapp.core.data.constants


fun getOffsetByPage(page: Int): Int{
    return if(page == 1) 0 else (page-1) * (Constants.PAGE_ITEMS_COUNT)
}
