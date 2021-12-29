package tgd.company.educurrencyconverter.domain.mapper

import tgd.company.educurrencyconverter.data.cbr.repository.model.DataModel
import tgd.company.educurrencyconverter.domain.exception.NotMappingDomainModelObject
import tgd.company.educurrencyconverter.domain.model.DomainModel

class DataToDomainModelMapper {

    fun call(input: DataModel?): DomainModel {
        return when (input) {
            is DataModel.Success<*> -> DomainModel.Success(input.data)
            is DataModel.Loading -> DomainModel.Loading
            is DataModel.Error -> DomainModel.Error(input.exception)
            else -> DomainModel.Error(NotMappingDomainModelObject())
        }
    }
}