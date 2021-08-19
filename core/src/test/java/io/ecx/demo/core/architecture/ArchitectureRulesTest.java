package io.ecx.demo.core.architecture;

import org.apache.sling.models.annotations.Model;
import org.osgi.annotation.versioning.Version;
import org.osgi.service.component.annotations.Component;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = { ArchitectureRulesTest.PROJECT_ROOT_PACKAGE }, importOptions = { ImportOption.DoNotIncludeTests.class,
  ImportOption.DoNotIncludeJars.class })
class ArchitectureRulesTest {

    static final String PROJECT_ROOT_PACKAGE = "${corePackage}";

    private static final String RECORDS_PACKAGE = "..records..";
    private static final String UTILS_PACKAGE = "..utils..";
    private static final String MODELS_PACKAGE = "..models..";
    private static final String SERVLETS_PACKAGE = "..servlets..";
    private static final String SERVICES_PACKAGE = "..services";
    private static final String SERVICES_IMPL_PACKAGE = "..services.impl..";

    // General rules

    @ArchTest
    private static final ArchRule noGenericExceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private static final ArchRule noJavaUtilLogging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    private static final ArchRule noJodaDatetime = NO_CLASSES_SHOULD_USE_JODATIME;

    @ArchTest
    private static final ArchRule noStandardStreamAccess = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    private static final ArchRule noCyclesInMainPackages = slices().matching(PROJECT_ROOT_PACKAGE + ".(*)..").should().beFreeOfCycles();

    // Records (POJOs)

    @ArchTest
    private static final ArchRule recordsHaveNoInternalDependencies = noClasses()
                                                                        .that()
                                                                        .resideInAPackage(RECORDS_PACKAGE)
                                                                        .and()
                                                                        .areNotAnnotatedWith(Version.class)
                                                                        .should()
                                                                        .dependOnClassesThat()
                                                                        .resideInAnyPackage(MODELS_PACKAGE, SERVICES_PACKAGE, SERVLETS_PACKAGE);

    // Utils

    @ArchTest
    private static final ArchRule utilsHaveOnlyPrivateConstructors = classes()
                                                                       .that()
                                                                       .resideInAPackage(UTILS_PACKAGE)
                                                                       .and()
                                                                       .areNotAnnotatedWith(Version.class)
                                                                       .should()
                                                                       .haveOnlyPrivateConstructors();

    @ArchTest
    private static final ArchRule utilsHaveOnlyStaticMethods = methods()
                                                                 .that()
                                                                 .areDeclaredInClassesThat()
                                                                 .resideInAPackage(UTILS_PACKAGE)
                                                                 .should()
                                                                 .beStatic();

    // Sling models

    @ArchTest
    private static final ArchRule allSlingModelsAreAnnotated = classes()
                                                                 .that()
                                                                 .resideInAPackage(MODELS_PACKAGE)
                                                                 .and()
                                                                 .areNotAnnotatedWith(Version.class)
                                                                 .should()
                                                                 .beAnnotatedWith(Model.class);

    @ArchTest
    private static final ArchRule allSlingModelsHaveCorrectNameSuffix = classes()
                                                                          .that()
                                                                          .resideInAPackage(MODELS_PACKAGE)
                                                                          .and()
                                                                          .areNotAnnotatedWith(Version.class)
                                                                          .should()
                                                                          .haveSimpleNameEndingWith("Model");

    @ArchTest
    private static final ArchRule slingModelsDoNotDependOnServlets = noClasses()
                                                                       .that()
                                                                       .resideInAPackage(MODELS_PACKAGE)
                                                                       .and()
                                                                       .areNotAnnotatedWith(Version.class)
                                                                       .should()
                                                                       .dependOnClassesThat()
                                                                       .resideInAPackage(SERVLETS_PACKAGE);

    @ArchTest
    private static final ArchRule slingModelsHaveNoStaticMethods = methods()
                                                                     .that()
                                                                     .areDeclaredInClassesThat()
                                                                     .resideInAPackage(MODELS_PACKAGE)
                                                                     .should()
                                                                     .notBeStatic()
                                                                     .orShould()
                                                                     .haveModifier(com.tngtech.archunit.core.domain.JavaModifier.SYNTHETIC);

    // Services

    @ArchTest
    private static final ArchRule servicesPackageContainsOnlyInterfaces = classes()
                                                                            .that()
                                                                            .resideInAPackage(SERVICES_PACKAGE)
                                                                            .and()
                                                                            .areNotAnnotatedWith(Version.class)
                                                                            .should()
                                                                            .beInterfaces();

    @ArchTest
    private static final ArchRule allServicesHaveCorrectNameSuffix = classes()
                                                                       .that()
                                                                       .resideInAPackage(SERVICES_PACKAGE)
                                                                       .and()
                                                                       .areNotAnnotatedWith(Version.class)
                                                                       .should()
                                                                       .haveSimpleNameEndingWith("Service");

    @ArchTest
    private static final ArchRule allServicesImplementationsAreAnnotated = classes()
                                                                             .that()
                                                                             .resideInAPackage(SERVICES_IMPL_PACKAGE)
                                                                             .and()
                                                                             .areNotAnnotatedWith(Version.class)
                                                                             .should()
                                                                             .beAnnotatedWith(Component.class);

    @ArchTest
    private static final ArchRule allServiceImplementationsHaveCorrectNameSuffix = classes()
                                                                                     .that()
                                                                                     .resideInAPackage(SERVICES_IMPL_PACKAGE)
                                                                                     .and()
                                                                                     .areNotAnnotatedWith(Version.class)
                                                                                     .should()
                                                                                     .haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    private static final ArchRule noServicesImplementationsAreInterfaces = classes()
                                                                             .that()
                                                                             .resideInAPackage(SERVICES_IMPL_PACKAGE)
                                                                             .and()
                                                                             .areNotAnnotatedWith(Version.class)
                                                                             .should()
                                                                             .notBeInterfaces();

    @ArchTest
    private static final ArchRule serviceImplementationsDoNotDependOnServlets = noClasses()
                                                                                  .that()
                                                                                  .resideInAPackage(SERVICES_IMPL_PACKAGE)
                                                                                  .and()
                                                                                  .areNotAnnotatedWith(Version.class)
                                                                                  .should()
                                                                                  .dependOnClassesThat()
                                                                                  .resideInAPackage(SERVLETS_PACKAGE);

    @ArchTest
    private static final ArchRule serviceImplementationsHaveNoStaticMethods = methods()
                                                                                .that()
                                                                                .areDeclaredInClassesThat()
                                                                                .resideInAPackage(SERVICES_IMPL_PACKAGE)
                                                                                .should()
                                                                                .notBeStatic()
                                                                                .orShould()
                                                                                .haveModifier(
                                                                                  com.tngtech.archunit.core.domain.JavaModifier.SYNTHETIC);

    // Servlets

    @ArchTest
    private static final ArchRule allServletsAreAnnotated = classes()
                                                              .that()
                                                              .resideInAPackage(SERVLETS_PACKAGE)
                                                              .and()
                                                              .areNotAnnotatedWith(Version.class)
                                                              .should()
                                                              .beAnnotatedWith(Component.class);

    @ArchTest
    private static final ArchRule allServletsHaveCorrectNameSuffix = classes()
                                                                       .that()
                                                                       .resideInAPackage(SERVLETS_PACKAGE)
                                                                       .and()
                                                                       .areNotAnnotatedWith(Version.class)
                                                                       .should()
                                                                       .haveSimpleNameEndingWith("Servlet");

    @ArchTest
    private static final ArchRule servletsHaveNoStaticMethods = methods()
                                                                  .that()
                                                                  .areDeclaredInClassesThat()
                                                                  .resideInAPackage(SERVLETS_PACKAGE)
                                                                  .should()
                                                                  .notBeStatic()
                                                                  .orShould()
                                                                  .haveModifier(com.tngtech.archunit.core.domain.JavaModifier.SYNTHETIC);

}