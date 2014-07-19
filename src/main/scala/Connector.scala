package io.yard.connector.api

import scala.concurrent.Future

import play.api.libs.json.{Reads, Writes}

import io.yard.common.models.{Organization, Module, ModuleConfiguration, Provider, ProviderConfiguration}

trait Connector {
  def orgs(implicit reader: Reads[Organization]): Seq[Organization]

  def read[T <: ProviderConfiguration](provider: Provider, organization: Option[Organization])(implicit reader: Reads[T]): Future[Option[T]]

  def save[T <: ProviderConfiguration](provider: Provider, organization: Option[Organization])(implicit writer: Writes[T]): Future[Boolean]

  def read[T <: ModuleConfiguration](module: Module, organization: Option[Organization])(implicit reader: Reads[T]): Future[Option[T]]

  def save[T <: ModuleConfiguration](module: Module, organization: Option[Organization])(implicit writer: Writes[T]): Future[Boolean]

  object organizations {
    import io.yard.common.models.Organization

    def all: Seq[Organization] = orgs
    def default: Organization = orgs.apply(0)
    def byNameOption(name: String): Option[Organization] = orgs.find( _.name == name )
    def byName(name: String): Organization = organizations.byNameOption(name) getOrElse organizations.default
    def from(value: String): Organization = organizations.byName(value)
  }
}
